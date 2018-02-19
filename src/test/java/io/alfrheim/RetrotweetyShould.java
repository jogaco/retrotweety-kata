package io.alfrheim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZonedDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RetrotweetyShould {

    @Mock
    private Console console;

    @Test
    public void print_the_superman_message() throws Exception {
        Clock clock = new Clock();
        Clock mockClock = spy(clock);

        ZonedDateTime now = ZonedDateTime.now();

        when(mockClock.getTime()).thenReturn(now, now.plusSeconds(1));
        Retrotweety retrotweety = new Retrotweety(console, mockClock);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Superman");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (1 second ago)");
    }

    @Test
    public void print_the_followers_message_on_the_wall() throws Exception {
        Clock clock = new Clock();
        Clock mockClock = spy(clock);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime after = now.plusSeconds(2);
        when(mockClock.getTime()).thenReturn(now, after);

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
