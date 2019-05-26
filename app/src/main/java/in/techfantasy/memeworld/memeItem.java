package in.techfantasy.memeworld;

public class memeItem {
    private String imgUrl;
    private String msg;

    public memeItem(String imgUrl, String msg) {
        this.imgUrl = imgUrl;
        this.msg = msg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
