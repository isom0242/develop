package com.example.builder;

public class BuilderPattern {

	public static void main(String[] args) {
		AlertDialog dialog = new AlertDialog
				.Builder("require1", "require2")
				.setOption1("option1")
				.build();
		dialog.show();
	}
}

class AlertDialog {
	private Builder builder; // 引数を管理
	
	private AlertDialog(Builder builder) {
		this.builder = builder;
	}
	
	public void show() {
		System.out.println(builder.req1);
		System.out.println(builder.req2);
		System.out.println(builder.opt1);
	}
	
	/**
	 * 引数の処理はすべてBuilderクラスが行う
	 */
	public static class Builder {
		private String req1;
		private String req2;
		private String opt1 = "";
		
		public Builder(String req1, String req2) {
			this.req1 = req1;
			this.req2 = req2;
		}
		
		public Builder setOption1(String opt1) {
			this.opt1 = opt1;
			return this;
		}
		
		public AlertDialog build() {
			return new AlertDialog(this);
		}
	}
}


