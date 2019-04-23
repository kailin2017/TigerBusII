package com.kailin.architecture_model.util;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public final class KailinDiffUtil {

    private static final AtomicReference<KailinDiffUtil> reference = new AtomicReference<>();

    public static KailinDiffUtil getInstance() {
        while (true) {
            KailinDiffUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new KailinDiffUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private KailinDiffUtil() {
    }

    public <T> void diff(RecyclerView.Adapter adapter, ArrayList<T> oldData, ArrayList<T> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new MyDiffCallBack<>(oldData, newData), true);
        diffResult.dispatchUpdatesTo(adapter);
    }

    private static final class MyDiffCallBack<T> extends DiffUtil.Callback {

        private final ArrayList<T> oldData;
        private final ArrayList<T> newData;

        public MyDiffCallBack(ArrayList<T> oldData, ArrayList<T> newData) {
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldPosition, int newPosition) {
            return oldData.get(oldPosition).equals(newData.get(newPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldPosition, int newPosition) {
            return oldData.get(oldPosition).equals(newData.get(newPosition));
        }

        @Nullable
        public Object getChangePayload(int oldPosition, int newPosition) {
            return null;
        }

    }
}
