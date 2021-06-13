package praktikum.smoker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main{
    public static void main(String[] args) {

         List<Dealer> dealers = createDealers();
//         dealers.stream().forEach((dealer -> dealer.giveStuff()));
var de1 = new Dealer("test");
de1.giveStuff();


        }

    static List<Dealer> createDealers () {
        return IntStream.range(0, 2).boxed().map(i -> new Dealer("dealer" + i)).collect(Collectors.toList());

    }
}
