package com.weixuan.shenxin.entity;


import java.io.Serializable;
import java.util.List;

public class FistPlayerVo implements Serializable {
	private static final long serialVersionUID = -3819747146480586074L;
	private String id;
	private String playerId;
	private String playerName;
	private String playerNo;
	private String bloc;
	private String isFirst;
	private List<Item> items;
	public class Item{
		private String time;
		private String type;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getBloc() {
		return bloc;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}
}
