package entity;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;
import java.util.List;

@Entity
public class Account {
    @Id
    private String email;
    private String password;
    private String avatar;
    private long createdAtMLS;
    private long updatedAtMLS;
    private long deletedAtMLS;
    private int status;
    // 1: create, 2: confirm, 0: delete

    List<Ref<ActiveCode>> listActiveCode;

    public Account(){
        this.createdAtMLS = Calendar.getInstance().getTimeInMillis();
        this.updatedAtMLS = Calendar.getInstance().getTimeInMillis();
        this.status = 1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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


    public static final class Builder {
        List<Ref<ActiveCode>> listActiveCode;
        private String email;
        private String password;
        private String avatar;

        private Builder() {
        }

        public static Builder anAccount() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder withListActiveCode(List<Ref<ActiveCode>> listActiveCode) {
            this.listActiveCode = listActiveCode;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);
            account.setAvatar(avatar);
            account.listActiveCode = this.listActiveCode;
            return account;
        }
    }
}
