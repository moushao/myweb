package tech.moushao.www.imp;

import java.util.List;

import tech.moushao.www.bean.Article;
import tech.moushao.www.dao.CustomerDao;

public class UserServiceImpl implements BusinessService {
	private CustomerDao cDao = new CustomerDaoMySQLImpl();

	public List<Article> findAllCustomers() {
		return cDao.findAll();
	}

	public void addArticle(Article c) {
		cDao.save(c);
	}

	public void deleteArticle(Integer customerId) {
		cDao.del(customerId);
	}

	public void updateArticle(Article c) {
		if (c.getTitle_id() == -1)
			return;
		cDao.update(c);
	}

	public Article findArticleById(Integer customerId) {
		return cDao.findOne(customerId);
	}

}
