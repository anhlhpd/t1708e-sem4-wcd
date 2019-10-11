package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Calendar;

@Entity
public class CrawlerSource {

    @Id
    private long id;
    private String url;
    private String linkSelector;
    private int linkLimit;

    private String titleSelector;
    private String descriptionSelector;
    private String contentSelector;
    private String authorSelector;

    @Index
    private long createdAtMLS;
    @Index
    private long updatedAtMLS;
    @Index
    private long deletedAtMLS;
    @Index
    private int status;

    public CrawlerSource() {
        this.id = Calendar.getInstance().getTimeInMillis();
        this.createdAtMLS = Calendar.getInstance().getTimeInMillis();
        this.updatedAtMLS = Calendar.getInstance().getTimeInMillis();
        this.status = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkSelector() {
        return linkSelector;
    }

    public void setLinkSelector(String linkSelector) {
        this.linkSelector = linkSelector;
    }

    public int getLinkLimit() {
        return linkLimit;
    }

    public void setLinkLimit(int linkLimit) {
        this.linkLimit = linkLimit;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public void setTitleSelector(String titleSelector) {
        this.titleSelector = titleSelector;
    }

    public String getDescriptionSelector() {
        return descriptionSelector;
    }

    public void setDescriptionSelector(String descriptionSelector) {
        this.descriptionSelector = descriptionSelector;
    }

    public String getContentSelector() {
        return contentSelector;
    }

    public void setContentSelector(String contentSelector) {
        this.contentSelector = contentSelector;
    }

    public String getAuthorSelector() {
        return authorSelector;
    }

    public void setAuthorSelector(String authorSelector) {
        this.authorSelector = authorSelector;
    }

    public long getCreatedAtMLS() {
        return createdAtMLS;
    }

    public void setCreatedAtMLS(long createdAtMLS) {
        this.createdAtMLS = createdAtMLS;
    }

    public long getUpdatedAtMLS() {
        return updatedAtMLS;
    }

    public void setUpdatedAtMLS(long updatedAtMLS) {
        this.updatedAtMLS = updatedAtMLS;
    }

    public long getDeletedAtMLS() {
        return deletedAtMLS;
    }

    public void setDeletedAtMLS(long deletedAtMLS) {
        this.deletedAtMLS = deletedAtMLS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isValid() {
        return true;
    }

    public static final class Builder {
        private String url;
        private String linkSelector;
        private int linkLimit;
        private String titleSelector;
        private String descriptionSelector;
        private String contentSelector;
        private String authorSelector;

        private Builder() {
        }

        public static Builder aCrawlerSource() {
            return new Builder();
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withLinkSelector(String linkSelector) {
            this.linkSelector = linkSelector;
            return this;
        }

        public Builder withLinkLimit(int linkLimit) {
            this.linkLimit = linkLimit;
            return this;
        }

        public Builder withTitleSelector(String titleSelector) {
            this.titleSelector = titleSelector;
            return this;
        }

        public Builder withDescriptionSelector(String descriptionSelector) {
            this.descriptionSelector = descriptionSelector;
            return this;
        }

        public Builder withContentSelector(String contentSelector) {
            this.contentSelector = contentSelector;
            return this;
        }

        public Builder withAuthorSelector(String authorSelector) {
            this.authorSelector = authorSelector;
            return this;
        }

        public CrawlerSource build() {
            CrawlerSource crawlerSource = new CrawlerSource();
            crawlerSource.setUrl(url);
            crawlerSource.setLinkSelector(linkSelector);
            crawlerSource.setLinkLimit(linkLimit);
            crawlerSource.setTitleSelector(titleSelector);
            crawlerSource.setDescriptionSelector(descriptionSelector);
            crawlerSource.setContentSelector(contentSelector);
            crawlerSource.setAuthorSelector(authorSelector);
            return crawlerSource;
        }
    }
}
