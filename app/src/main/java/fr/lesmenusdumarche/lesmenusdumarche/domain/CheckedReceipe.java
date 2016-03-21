package fr.lesmenusdumarche.lesmenusdumarche.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btessiau on 21/03/16.
 */
public class CheckedReceipe implements Parcelable {

    private List<String> mReceipes;

    public CheckedReceipe(List<String> receipe)
    {
        super();
        this.mReceipes = receipe;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mReceipes.toString());
    }

    public static final Parcelable.Creator<CheckedReceipe> CREATOR
            = new Parcelable.Creator<CheckedReceipe>() {
        public CheckedReceipe createFromParcel(Parcel source) {

            return new CheckedReceipe(source);
        }

        public CheckedReceipe[] newArray(int size) {
            return new CheckedReceipe[size];
        }
    };

    private CheckedReceipe(Parcel in) {
        this.mReceipes = new ArrayList<String>();
        in.readList(this.mReceipes,null);
    }

    public List<String> getmReceipes() {return mReceipes; }
}
