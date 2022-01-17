package com.gohb.params.bo.notify;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName notify
 */
@TableName(value ="notify")
@Data
public class NotifyBO implements Serializable {
    /**
     * 通知id
     */
    @TableId(value = "notify_id", type = IdType.AUTO)
    private Long notifyId;

    /**
     * 通知时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 通知内容
     */
    @TableField(value = "notify_content")
    private String notifyContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NotifyBO other = (NotifyBO) that;
        return (this.getNotifyId() == null ? other.getNotifyId() == null : this.getNotifyId().equals(other.getNotifyId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNotifyId() == null) ? 0 : getNotifyId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", notifyId=").append(notifyId);
        sb.append(", createTime=").append(createTime);
        sb.append(", notifyContent=").append(notifyContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
