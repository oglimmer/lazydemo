

org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor#preHandle

    * creates the EntityManager 
    * EntityManager creates org.hibernate.internal.SessionImpl (this implements EntityManager and Session)
    * puts a ref of EntityManager  into TransactionSynchronizationManager


injected EntityManager is of type LocalContainerEntityManagerFctorayBean

Lists in your Entity are loaded as:

    org.hibernate.collection.internal.PersistentBag


org.hibernate.engine.internal.StatefulPersistenceContext implements org.hibernate.engine.spi.PersistenceContext
    * holds references to all entity to make lazy-loading possible



