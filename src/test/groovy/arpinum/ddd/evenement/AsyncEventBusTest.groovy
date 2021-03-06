package arpinum.ddd.evenement

import arpinum.ddd.BaseAggregate
import arpinum.infrastructure.bus.event.AsyncEventBus
import com.google.common.collect.Sets
import spock.lang.Specification

class AsyncEventBusTest extends Specification {

    def "waits to propagage"() {
        given:
        def captor = new FakeEventCaptor()
        AsyncEventBus bus = busAvec(captor)

        when:
        bus.publish(new FakeEvent())

        then:
        !captor.called
        bus.events.get().size() == 1
    }

    def "executes events after commands"() {
        given:
        def captor = new FakeEventCaptor()
        AsyncEventBus bus = busAvec(captor)

        when:
        bus.publish(new FakeEvent())
        bus.afterExecution()

        then:
        bus.events.get().size() == 0
    }

    private busAvec(EventCaptor<? extends Event> captor) {
        new AsyncEventBus(Sets.newHashSet(), Sets.newHashSet(captor))
    }

    static class FakeEvent extends Event<FakeAggregate> {

    }

    static class FakeAggregate extends BaseAggregate<String> {

    }

    static class FakeEventCaptor implements EventCaptor<FakeEvent> {

        boolean called

        @Override
        void executeEvent(FakeEvent evenement) {
            called = true
        }
    }

}
