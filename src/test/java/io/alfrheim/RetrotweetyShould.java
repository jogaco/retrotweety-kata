package io.alfrheim;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class RetrotweetyShould {

    @Mock
    private  Console console;

    @Test
    public void print_the_superman_message() throws Exception {
        Retrotweety retrotweety = new Retrotweety(console);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        retrotweety.command("Superman");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (0 second ago)");
    }

    @Test
    public void print_the_followers_message_on_the_wall() throws Exception {
        Retrotweety retrotweety = new Retrotweety(console);

        retrotweety.command("Superman -> Hello! I'm superawseome!");
        Thread.sleep(2000);
        retrotweety.command("Spiderman follows Superman");
        retrotweety.command("Spiderman -> Hey Superman! I'm following you!");

        retrotweety.command("Spiderman wall");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Spiderman - Hey Superman! I'm following you! (0 second ago)");
        inOrder.verify(console).printLine("Superman - Hello! I'm superawseome! (2 seconds ago)");
    }

}
