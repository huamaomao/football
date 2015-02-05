package com.vxfc.shenxin.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AgainstVo implements Serializable{
	private static final long serialVersionUID = -4101802171908248427L;
	private String arbitre;
	private String entraineur;
	private String formationUsed;
	private List<Item> items;
	public Map<Integer,AgainstVo.Item> team;
	public Item createItem() {
		return new Item();
	}
	public  class Item {
		private String playerId;
		private String playerName;
		private String playerNo;
		private String formationPlace;
		public String getPlayerId() {
			return playerId;
		}
		public void setPlayerId(String playerId) {
			this.playerId = playerId;
		}
		public String getPlayerName() {
			return playerName;
		}
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
		public String getPlayerNo() {
			return playerNo;
		}
		public void setPlayerNo(String playerNo) {
			this.playerNo = playerNo;
		}
		public String getFormationPlace() {
			return formationPlace;
		}
		public void setFormationPlace(String formationPlace) {
			this.formationPlace = formationPlace;
		}
		
	}

	public String getArbitre() {
		return arbitre;
	}


	public void setArbitre(String arbitre) {
		this.arbitre = arbitre;
	}


	public String getEntraineur() {
		return entraineur;
	}


	public void setEntraineur(String entraineur) {
		this.entraineur = entraineur;
	}


	public String getFormationUsed() {
		return formationUsed;
	}


	public void setFormationUsed(String formationUsed) {
		this.formationUsed = formationUsed;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
