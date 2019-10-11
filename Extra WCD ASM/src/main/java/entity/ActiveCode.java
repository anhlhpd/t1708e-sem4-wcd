package entity;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class ActiveCode {

    @Id
    private String code;
    @Index
    private long createdAtMLS;
    @Load
    @Index
    Ref<Account> account;
    @Index
    private int status;
    // 1: create, 2: expired, 3: used

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCreatedAtMLS() {
        return createdAtMLS;
    }

    public void setCreatedAtMLS(long createdAtMLS) {
        this.createdAtMLS = createdAtMLS;
    }

    public Ref<Account> getAccount() {
        return account;
    }

    public void setAccount(Ref<Account> account) {
        this.account = account;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isValid(){
        return true;
    }

    public static final class Builder {
        Ref<Account> account;
        private String code;
        private long createdAtMLS;
        private int status;

        private Builder() {
        }

        public static Builder anActiveCode() {
            return new Builder();
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withCreatedAtMLS(long createdAtMLS) {
            this.createdAtMLS = createdAtMLS;
            return this;
        }

        public Builder withAccount(Ref<Account> account) {
            this.account = account;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ActiveCode build() {
            ActiveCode activeCode = new ActiveCode();
            activeCode.setCode(code);
            activeCode.setCreatedAtMLS(createdAtMLS);
            activeCode.setAccount(account);
            activeCode.setStatus(status);
            return activeCode;
        }
    }
}
