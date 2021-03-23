package com.ashunevich.android_retrofit2_test;

import java.util.List;

import androidx.annotation.Nullable;

public class DiffUtiiCallbackAdapter extends androidx.recyclerview.widget.DiffUtil.Callback {

    List<ItemJSON> newList;
    List<ItemJSON> oldlist;

    public DiffUtiiCallbackAdapter(List<ItemJSON> oldlist, List<ItemJSON>newList) {
        this.oldlist = oldlist;
        this.newList = newList;
    }


    @Override
    public int getOldListSize() {
        return oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).getFactNumber() == newList.get(newItemPosition).getFactNumber();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
