package jp.ne.naokiur.em.dto;

public class PostDto {
    private String postId;
    private String postName;

    public PostDto(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
