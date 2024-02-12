import java.util.LinkedList;
import java.util.List;

public class User {

	public static List<Integer> lottolist = new LinkedList<>();
	public static int amount = 10000;

	public static List<Integer> getLottolist() {
		return lottolist;
	}

	public static void setLottolist(List<Integer> lottolist) {
		User.lottolist = lottolist;
	}

	public static void setAmount(int amount) {
		User.amount = amount;
	}


	public static int getAmount() {
		return amount;
	}

	public static void addAmount(int rechargeAmount) {
		amount += rechargeAmount;
	}
}
