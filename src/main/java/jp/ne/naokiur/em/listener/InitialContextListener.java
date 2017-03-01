package jp.ne.naokiur.em.listener;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.ne.naokiur.em.dao.EmployeeAccessorImpl;
import jp.ne.naokiur.em.dao.PostAccessorImpl;
import jp.ne.naokiur.em.dao.UsersAccessorImpl;
import jp.ne.naokiur.em.dto.PostDto;
import jp.ne.naokiur.em.dto.UserDto;

public class InitialContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initializeDB();
    }

    private void initializeDB() {
        UsersAccessorImpl.INSTANCE.createTable();
        EmployeeAccessorImpl.INSTANCE.createTable();
        PostAccessorImpl.INSTANCE.createTable();

        String initialAccountUser = "admin";
        String initialAccountPassword = "admin";
        String initialAccount = UsersAccessorImpl.INSTANCE.selectUserId(initialAccountUser, initialAccountPassword);

        if (initialAccount == null || "".equals(initialAccount)) {
            UsersAccessorImpl.INSTANCE.insertUser(new UserDto(initialAccountUser, initialAccountPassword));
        }

        String postCount = PostAccessorImpl.INSTANCE.selectPostCount();
        if (postCount == null || "".equals(postCount) || "0".equals(postCount)) {

            List<PostDto> postMap = new LinkedList<PostDto>() {
                private static final long serialVersionUID = 1L;

                {
                    add(new PostDto("11", "第1ビジネスサービスグループ 年金・保険"));
                    add(new PostDto("12", "第1ビジネスサービスグループ 金融"));
                    add(new PostDto("21", "第2ビジネスサービスグループ AMP"));
                    add(new PostDto("22", "第2ビジネスサービスグループ テスト"));
                    add(new PostDto("31", "第3ビジネスサービスグループ AMP"));
                    add(new PostDto("32", "第3ビジネスサービスグループ 基盤・先端技術"));
                }
            };

            for (PostDto post : postMap) {
                PostAccessorImpl.INSTANCE.insertUser(post);
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
