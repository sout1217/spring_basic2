package vuejs.springboot.mysql.backend.domain.model.account.events.event;

import org.springframework.util.Assert;
import vuejs.springboot.mysql.backend.domain.application.common.event.DomainEvent;
import vuejs.springboot.mysql.backend.domain.model.account.Account;

public class AccountRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = 2580061707540917880L;

  private Account user;

  public AccountRegisteredEvent(Account user) {
    super(user);
    Assert.notNull(user, "Parameter `user` must not be null");
    this.user = user;
  }

  public Account getUser() {
    return this.user;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AccountRegisteredEvent that = (AccountRegisteredEvent) o;
    return that.user.equals(this.user);
  }

  public int hashCode() {
    return this.user.hashCode();
  }

  public String toString() {
    return "UserRegisteredEvent{" +
      "user='" + user + '\'' +
      "timestamp='" + getTimestamp() + '\'' +
      '}';
  }
}
