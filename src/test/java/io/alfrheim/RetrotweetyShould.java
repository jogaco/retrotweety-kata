package io.alfrheim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrotweetyShould {

    @Mock
    private Console console;

    @Test
    public void print_the_superman_message() throws Exception {
        ClockFactory clock = new ClockFactory();
        ClockFactory mockClock = spy(clock);

        Clock clock1SecondElapsed = spy(new Clock());
        when(clock1SecondElapsed.elapsedTime()).thenReturn(Duration.ofSeconds(1));

        when(mockClock.startClock()).thenReturn(clock1SecondElapsed);

        Retrotweety retrotweety = new Retrotweety(console, mockClock);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Superman");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (1 second ago)");
    }

    @Test
    public void print_the_followers_message_on_the_wall() throws Exception {
        ClockFactory clock = new ClockFactory();
        ClockFactory mockClock = spy(clock);

        Clock clock0SecondElapsed = spy(new Clock());
        when(clock0SecondElapsed.elapsedTime()).thenReturn(Duration.ofSeconds(0));

        Clock clock2SecondElapsed = spy(new Clock());
        when(clock2SecondElapsed.elapsedTime()).thenReturn(Duration.ofSeconds(2));

        when(mockClock.startClock()).thenReturn(clock2SecondElapsed, clock0SecondElapsed);

        Retrotweety retrotweety = new Retrotweety(console, mockClock);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Spiderman follows Superman");
        retrotweety.command("Spiderman -> Hey Superman! I'm following you!");
        retrotweety.command("Spiderman wall");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Spiderman - Hey Superman! I'm following you! (0 second ago)");
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (2 seconds ago)");
    }

}
