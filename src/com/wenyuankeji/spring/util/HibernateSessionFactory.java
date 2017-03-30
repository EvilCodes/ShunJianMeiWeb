package com.wenyuankeji.spring.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {
	private static Configuration configuration = null;
	private static SessionFactory sessionFactory = null;
	/*
	 * ServiceRegistry是Hibernate4.X新增接口，
	 * 应用于Hibernate的配置或者服务等将统一向这个ServiceRegistry注册后才能生效。
	 * 所以需要构建一个ServiceRegistry对象
	 * ，将配置信息向它注册，然后Configuration对象根据从这个ServiceRegistry对象中获取配置信息生成SessionFactory
	 * 。 Hibernate4的配置入口不再是Configuration对象，而是ServiceRegistry对象，
	 * Configuration对象将通过ServiceRegistry对象获取配置信息。 hibernate4
	 * 源码位置：org.hibernate.service.ServiceRegistryBuilder 具体可参看hibernate源码。以及API
	 */
	private static ServiceRegistry serviceRegistry = null;

	static {
		try {
			// 首先获取配置信息
			configuration = new Configuration().configure();// 读取hibernate-cfg.xml,如没有会报错
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();

			// 创建Session Factory
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/** 获得Session 2013-10-18 **/
	public static Session getSession() {
		Session session = null;
		if (null == session || false == session.isOpen()) {
			session = sessionFactory.openSession();
		}

		return session;
	}

	/** 关闭Session 2013-10-18 **/
	public static void closeSession(Session session) {
		try {
			if (null != session && session.isOpen())
				session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}