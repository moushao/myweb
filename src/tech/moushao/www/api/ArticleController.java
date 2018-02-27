package tech.moushao.www.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.moushao.www.bean.Article;
import tech.moushao.www.imp.BusinessService;
import tech.moushao.www.imp.UserServiceImpl;
import tech.moushao.www.utils.FillBeanUtil;
import tech.moushao.www.utils.JsonUtils;
import tech.moushao.www.utils.TextUtils;

@WebServlet(name = "article", urlPatterns = "/article")
public class ArticleController extends HttpServlet {
    private BusinessService s = new UserServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        //		String encoding = "UTF-8";
        //		request.setCharacterEncoding(encoding);
        //		response.setContentType("text/html;charset=" + encoding);
        String op = request.getParameter("op");
        if ("showAllArticles".equals(op)) {
            showAllCustomers(request, response);
        } else if ("findArticle".equals(op)) {
            findOneArticle(request, response);
        } else if ("addArticle".equals(op)) {
            addArticle(request, response);
        }

    }

    private void findOneArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Article article = s.findArticleById(Integer.valueOf(request.getParameter("title_id")));
        response.getWriter().write(JsonUtils.getJsonString("100", "指定文章查询成功", article));
    }

    private void showAllCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Article> cs = s.findAllCustomers();
        response.getWriter().write(JsonUtils.getJsonString("100", "全部文章查询成功", cs));
    }

    /**
     * 添加文章
     */
    private void addArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //封装数据到FormBean
        Article article = FillBeanUtil.fillBean(request, Article.class);
        if (TextUtils.isEmpty(article.getTitle())) {
            response.getWriter().write(JsonUtils.getJsonString("500", "文章标题不能为空"));
        }
        if (TextUtils.isEmpty(article.getContent())) {
            response.getWriter().write(JsonUtils.getJsonString("500", "文章内容不能为空"));
        }
        s.addArticle(article);
        response.getWriter().write(JsonUtils.getJsonString("100", "文章添加成功"));
    }
}
