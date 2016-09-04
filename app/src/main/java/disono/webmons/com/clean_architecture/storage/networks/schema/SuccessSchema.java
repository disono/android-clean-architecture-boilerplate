package disono.webmons.com.clean_architecture.storage.networks.schema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class SuccessSchema {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("links")
    @Expose
    private Object links;
    @SerializedName("extra")
    @Expose
    private Object extra;

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return The links
     */
    public Object getLinks() {
        return links;
    }

    /**
     * @param links The links
     */
    public void setLinks(Object links) {
        this.links = links;
    }

    /**
     * @return The extra
     */
    public Object getExtra() {
        return extra;
    }

    /**
     * @param extra The extra
     */
    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
