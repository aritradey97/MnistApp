package com.btechviral.mnistapp;

public class Result {

    private final int mPrediction;
    private final float mProbability;
    private final long mTimeCost;

    public Result(float[] probs, long timeCost) {
        mPrediction = argmax(probs);
        mProbability = probs[mPrediction];
        mTimeCost = timeCost;
    }

    public int getPrediction() {
        return mPrediction;
    }

    public float getProbability() {
        return mProbability;
    }

    public long getTimeCost() {
        return mTimeCost;
    }

    private static int argmax(float[] probs) {
        int maxIdx = -1;
        float maxProb = 0.0f;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > maxProb) {
                maxProb = probs[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}