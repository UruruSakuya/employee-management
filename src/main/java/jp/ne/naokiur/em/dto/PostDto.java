package jp.ne.naokiur.em.dto;

public class PostDto {
    private String postCode;
    private String postName;

    public PostDto(String postCode, String postName) {
        this.postCode = postCode;
        this.postName = postName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postId) {
        this.postCode = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
