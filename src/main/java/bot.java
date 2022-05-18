import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

public class bot {

    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create("OTM0MTc2NjYxMDU2OTI5Nzky.GRpedn.tU4A67-xlvv1JGFIbUG1v71r2bNS1FEyerQXfw");

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->
                            Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                            }))
                    .then();

            // MessageCreateEvent example
            Mono<Void> SealCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();

                if (message.getContent().toLowerCase().startsWith("s")) {
                    String seal = message.getContent().toLowerCase();
                    int length = 0;
                    char temp;

                    for (int e = 0;e < seal.length();e++) {
                        temp = seal.charAt(e);
                        if (temp == 'e') {
                            length++;
                        }
                    }

                    System.out.printf ("Seal needs to be sent %s times", length);

                    for (length = length; length != 0; length = length - 1) {
                        System.out.println("Seal sent");
                        return message.getChannel()
                            .flatMap(channel -> channel.createMessage("https://images-ext-1.discordapp.net/external/0GSMDzdqiPfYYHprsVTQwZLQ2baVBr3X9mbTgF6Pr-M/https/media.discordapp.net/attachments/805119953370873906/900052804650041384/agony.png?width=220&height=178"));
                    }
                }

                return Mono.empty();
            }).then();

            // combine them!
            return printOnLogin.and(SealCommand);
        });

        login.block();
    }

}