package srp.com.fallrooz.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import srp.com.fallrooz.R;
import srp.com.fallrooz.adapter.slackloadingview.SlackLoadingView;


public class Loading {

	private Context mContext;
	private Dialog dialog;
	
	public Loading(Context context) {
		this.mContext = context;
	}
	
	public void showdialog() {
		try{
			dialog =new Dialog(mContext);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.loading);
			dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog.getWindow().getAttributes());
			lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			lp.height = WindowManager.LayoutParams.MATCH_PARENT;
			dialog.getWindow().setAttributes(lp);

			SlackLoadingView mLoadingView = (SlackLoadingView) dialog.findViewById(R.id.loading_view);
			mLoadingView.start();
			dialog.setCancelable(false);
			dialog.show();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void dismiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
	
}
