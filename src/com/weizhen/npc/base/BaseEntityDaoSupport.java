package com.weizhen.npc.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.chineseall.dams.common.paging.BaseQueryModel;
import com.chineseall.dams.common.paging.ConditionsAndNameValuePairs;
import com.chineseall.dams.common.paging.NameValuePair;
import com.chineseall.dams.common.paging.Paging;
import com.chineseall.dams.common.paging.PagingQueryResult;
import com.chineseall.dams.common.paging.SimpleQueryModelProcessor;
import com.fylaw.utils.CollectionUtils;
import com.fylaw.utils.EntityUtils;
import com.weizhen.npc.exception.ModelNotFoundException;

/**
 * 
 * @author y
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseEntityDaoSupport<T> extends HibernateDaoSupport {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Class<T> entityClass;

	/**
	 * 默认构造函数，子类Dao继承自该类的时候，传入实际参数化后的类型，将自动传入父类的类型中
	 */
	public BaseEntityDaoSupport() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 由于HibernateDaoSupport的sessionFactory属性的get和set方法都是final的，在本类中无法重写
	 * 故在此调用父类的setSessionFactory方法，设置sessionFactory
	 * 
	 * 另外实例化时，Autowired能根据类型进行自动装配，将Spring中托管的sessionFactory的bean装配到该方法的参数中来
	 * 
	 * @param sessionFactory
	 */
	@Autowired
	public void setBaseEntitySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);

	}

	/**
	 * hibernate的load方法，可使用到session缓存
	 * 
	 * @param id
	 * @return
	 */
	public T load(Serializable id) {
		return getHibernateTemplate().load(entityClass, id);

	}
	
	public T loadExists(Serializable id) {
		T t = load(id);
		
		if (null == t) throw new ModelNotFoundException("指定的记录不存在:" + id);
		
		return t;
	}

	/**
	 * hibernate的get方法，每一次都去数据库中查询
	 * 
	 * @param id
	 * @return
	 */
	public T get(Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}
	
	public T getExists(Serializable id) {
		T t = getHibernateTemplate().get(entityClass, id);
		
		if (null == t) throw new ModelNotFoundException("指定的记录不存在");
		
		return t;
	}

	/**
	 * 保存对象的方法
	 * 
	 * @param entity
	 * @return
	 */
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}

	/**
	 * 新增或保存对象，根据T中的ID来判断
	 * 
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity) {
		getHibernateTemplate().update(entity);
		return entity;

	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 * @return
	 */
	public T delete(T entity) {
		getHibernateTemplate().delete(entity);
		return entity;

	}

	/**
	 * 加载全部对象
	 * 
	 * @return
	 */
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(this.entityClass);
	}

	/**
	 * 根据条件查询对象
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List<T> find(String queryString, Object... values) {
		return (List<T>) getHibernateTemplate().find(queryString, values);

	}

	/**
	 * 屏蔽实现细节，即getHibernateTemplate()方法的调用
	 * 
	 * @param queryModel
	 * @return
	 */
	public List<T> findByQueryModel(BaseQueryModel<?> queryModel) {
		ConditionsAndNameValuePairs cavp = SimpleQueryModelProcessor.gen(queryModel);
		String hql = " from " + entityClass.getSimpleName() + " " + cavp.getWhereCondition() + " " + queryModel.getOrderCondition();
		return (List<T>) findByNameValuePairs(hql, cavp.getNameValuePairs());

	}

	/**
	 * 屏蔽实现细节，即getHibernateTemplate()方法的调用
	 * 
	 * @param queryString
	 * @param nameValuePairs
	 * @return
	 */
	public List<T> findByNameValuePairs(String queryString, List<NameValuePair> nameValuePairs) {
		if (EntityUtils.isEmpty(nameValuePairs)) {
			return getHibernateTemplate().find(queryString);
		}
		int size = nameValuePairs.size();
		String[] paramNames = new String[size];
		Object[] values = new Object[size];
		int index = 0;
		for (NameValuePair nvp : nameValuePairs) {
			paramNames[index] = nvp.getName();
			values[index] = nvp.getValue();
			index++;
		}
		return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
	}

	/**
	 * 屏蔽实现细节，即getHibernateTemplate()方法的调用
	 * 
	 * @param offset
	 *            起始条数
	 * @param pageSize
	 *            最大条数
	 * @param queryString
	 *            查询hql语句
	 * @param values
	 *            hql语句的参数
	 * @return
	 */
	public List<T> pagingFind(final Integer offset, final Integer pageSize, final String queryString, final Object... values) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);
				if (EntityUtils.notEmpty(values)) {
					for (int index = 0; index < values.length; index++) {
						query.setParameter(index, values[index]);
					}
				}
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 * @param paging
	 * @param values
	 * @return
	 */
	public PagingQueryResult<T> pagingQuery(String queryString, Paging paging, Object... values) {
		Integer offset;
		Integer pageSize;
		String sql = queryString.replaceAll(".* from |^from ", "select count(*) from ");

		Long total = (Long) getHibernateTemplate().find(sql, values).get(0);
		paging.setItemCount(total.intValue());

		offset = paging.getOffset();
		pageSize = paging.getRows();

		List<T> list = pagingFind(offset, pageSize, queryString, values);
		PagingQueryResult<T> result = new PagingQueryResult<T>(list, paging);
		return result;
	}

	/**
	 * 执行语句,并返回受影响的条数
	 * 
	 * @param queryString
	 *            语句
	 * @return 受影响的条数
	 */
	public int execute(String queryString) {
		return getHibernateTemplate().bulkUpdate(queryString);

	}

	/**
	 * 执行语句,并返回受影响的条数
	 * 
	 * @param queryString
	 *            语句
	 * @param values
	 *            条件参数
	 * @return 受影响的条数
	 */
	public int execute(String queryString, Object... values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 */
	public void executeSql(final String sql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createSQLQuery(sql).executeUpdate();
				return new ArrayList();
			}
		});

	}

	public PagingQueryResult<T> pagingQuery(BaseQueryModel<?> queryModel, Paging paging) {
		String ql = " from " + entityClass.getSimpleName() + " ";
		return (PagingQueryResult<T>) pagingQuery(ql, queryModel, paging);
	}

	public <E> PagingQueryResult<E> pagingQuery(String select, BaseQueryModel<?> queryModel, Paging paging) {
		return pagingQuery(select, null, queryModel, paging);
	}

	public <E> PagingQueryResult<E> pagingQuery(String select, String alias, BaseQueryModel<?> queryModel, Paging paging) {
		alias = alias == null ? "" : alias;
		ConditionsAndNameValuePairs canv = SimpleQueryModelProcessor.gen(queryModel, alias);
		select += canv.getWhereCondition() + queryModel.getOrderCondition();
		List<NameValuePair> nameValuePairs = canv.getNameValuePairs();

		return pagingQuery(select, nameValuePairs, paging);
	}

	public <E> PagingQueryResult<E> pagingQuery(String ql, Map<String, Object> conditionValues, Paging paging) {
		List<NameValuePair> nameValuePairs = convertMap2NameValuePairsList(conditionValues);
		return pagingQuery(ql, nameValuePairs, paging);
	}

	public <E> PagingQueryResult<E> pagingQuery(String ql, final List<NameValuePair> nameValuePairs, Paging paging) {
		Integer itemCount = count(ql, nameValuePairs).intValue();
		paging.setItemCount(itemCount);

		final String hql = ql;
		final int offset = paging.getOffset();
		final int rows = paging.getRows();
		List<E> records = getHibernateTemplate().executeFind(new HibernateCallback<List<E>>() {
			public List<E> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);

				if (EntityUtils.notEmpty(nameValuePairs)) {
					for (NameValuePair nvp : nameValuePairs) {
						Object parameterValue = nvp.getValue();
						if (CollectionUtils.isCollectionObject(parameterValue))
							query.setParameterList(nvp.getName(), CollectionUtils.toList(parameterValue));
						else
							query.setParameter(nvp.getName(), parameterValue);
					}
				}

				query.setFirstResult(offset);
				query.setMaxResults(rows);

				return query.list();
			}
		});

		return new PagingQueryResult<E>(records, paging);
	}

	private Long count(String ql, List<NameValuePair> nameValuePairs) {
		ql = "select count(*) " + ql.substring(ql.indexOf("from")).replaceAll(" fetch ", " ");
		Long counts = (Long) findByNameValuePairs(ql, nameValuePairs).get(0);

		return counts;
	}

	private List<NameValuePair> convertMap2NameValuePairsList(Map<String, Object> conditionValues) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : conditionValues.keySet()) {
			NameValuePair nameValuePair = new NameValuePair();
			nameValuePair.setName(key);
			nameValuePair.setValue(conditionValues.get(key));
			nameValuePairs.add(nameValuePair);
		}
		return nameValuePairs;
	}

	public void saveOrUpdate(final List<T> entities) {
		saveOrUpdate(entities, 1000);
	}

	public void saveOrUpdate(final List<T> entities, final int count) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				if (entities != null && entities.size() > 0) {
					try {
						session.beginTransaction();
						T entity = null;
						for (int i = 0; i < entities.size(); i++) {
							entity = (T) entities.get(i);
							session.save(entity);
							if (i % count == 0) {
								session.flush();
								session.clear();
							}
						}
						session.getTransaction().commit();
					} catch (Exception e) {
						e.printStackTrace();
						session.getTransaction().rollback();
					} finally {
						session.close();
					}
				}
				return null;
			}
		});
	}

	public void saveOrUpdateAll(List<T> entitites) {
		getHibernateTemplate().saveOrUpdateAll(entitites);
	}
	
	public <E> E updateEntity(E entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		
		return entity;
	}
	
	public Object findFirst(String queryString, Object...values) {
		List datas = getHibernateTemplate().find(queryString, values);;
		if (EntityUtils.notEmpty(datas))
			return datas.get(0);
		
		return null;
	}
	
	public <E> E nvl(E first, E second) {
		if (null == first) return second;
		
		return first;
	}
}
