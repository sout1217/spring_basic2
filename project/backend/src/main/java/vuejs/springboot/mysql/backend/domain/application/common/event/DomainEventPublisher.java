package vuejs.springboot.mysql.backend.domain.application.common.event;

public interface DomainEventPublisher {

  /**
   * Publish a domain event
   */
  void publish(DomainEvent event);
}
