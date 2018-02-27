package tech.moushao.www.imp;

import java.util.List;

import tech.moushao.www.bean.Article;
import tech.moushao.www.bean.User;

public interface BusinessService {
	/**
	 * @return
	 */
	List<Article> findAllCustomers();
	/**
	 * @param c
	 */
	void addArticle(Article c);
	/**
	 * 根据主键删除客户信息
	 * @param customerId
	 */
	void deleteArticle(Integer customerId);
	/**
	 * 更新客户信息
	 * @param c
	 * @throws IdIsNullEmpty 如果参数c的id值为null，抛出此异常
	 */
	void updateArticle(Article c);
	/**
	 * 根据主键查询客户信息
	 * @param customerId
	 * @return
	 */
	Article findArticleById(Integer customerId);
}
