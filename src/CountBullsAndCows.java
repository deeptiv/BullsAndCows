

public class CountBullsAndCows {


	char[] makerArray;

	public void initializeMakerArray(WordMakerPlayer wordMakerPlayer){

		makerArray = wordMakerPlayer.getWordMakerString().toCharArray(); 

	}

	public BullsAndCows countBullsAndCows(WordMakerPlayer wordMakerPlayer, WordBreakerPlayer wordBreakerPlayer){
		BullsAndCows  bullsAndCows = new BullsAndCows();
		char[] makerArray = wordMakerPlayer.getWordMakerString().toCharArray();
		char[] breakerArray = wordBreakerPlayer.getWordBreakerString().toCharArray();

		for (int i=0; i<4; i++){
			if(breakerArray[i] == makerArray[i]){
				int currentbulls = bullsAndCows.getBulls();
				bullsAndCows.setBulls(++currentbulls);
				makerArray[i] = '0';
				breakerArray[i] = '0';
			}
		}

		for (int i=0; i<4; i++){
			if(breakerArray[i] != '0'){
				for (int j=0; j<4; j++){
					if(breakerArray[i] == makerArray[j]){
						int currentCows = bullsAndCows.getCows();
						bullsAndCows.setCows(++currentCows);
						makerArray[j] = '0';
					}
				}
			}
		}

		return bullsAndCows;
	}
}
