package tech.moushao.www.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tech.moushao.www.bean.Article;
import tech.moushao.www.dao.CustomerDao;
import tech.moushao.www.utils.JdbcUtil;

public class CustomerDaoMySQLImpl implements CustomerDao {

    public List<Article> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM article ORDER BY title_id DESC");
            rs = stmt.executeQuery();
            List<Article> cs = new ArrayList<Article>();
            while (rs.next()) {
                Article c = new Article();
                c.setTitle_id(rs.getInt("title_id"));
                c.setTitle(rs.getString("title"));
                c.setContent("");
                cs.add(c);
            }
            return cs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }

    }

    public void save(Article c) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("insert into article (title,content,is_show,is_publish)  values (?,?,?,?)");
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getContent());
            stmt.setInt(3, 1);
            stmt.setInt(4, 1);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }

    }

    public void del(Integer customerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("delete from article where title_id=?");
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }

    }

    public void update(Article c) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("update article set id=?,name=?,sex=?,age=?");
            c.setTitle_id(rs.getInt("title_id"));
            c.setTitle(rs.getString("title"));
            c.setContent(rs.getString("content"));
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }

    }

    public Article findOne(Integer title_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("select * from article where title_id=?");
            stmt.setInt(1, title_id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Article c = new Article();
                c.setTitle_id(rs.getInt("title_id"));
                c.setTitle(rs.getString("title"));
                //				c.setContent(rs.getString("content".replace("\n", "").replace("\r", "")));
                c.setContent(rs.getString("content"));
                return c;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.release(rs, stmt, conn);
        }
    }

}
