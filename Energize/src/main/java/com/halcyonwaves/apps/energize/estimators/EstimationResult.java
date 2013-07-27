package com.halcyonwaves.apps.energize.estimators;

import android.os.Bundle;

/**
 * Representation of the results obtained by the estimation algorithm.
 * 
 * This class represents a wrapper to the information obtained by one of
 * the estimation algorithms.
 * 
 * @author Tim Huetz <tim@huetz.biz>
 * @since 0.7
 */
public final class EstimationResult {

	/**
	 * Reconstructs and instance of this class based on the data stored
	 * in the supplied Bundle.
	 * 
	 * @since 0.8
	 * @param from The Bundle form which the object should be reconstructed.
	 * @return An instance of this class based on the Bundle data.
	 */
	public static EstimationResult fromBundle( final Bundle from ) {
		final boolean charging = from.getBoolean( "charging" );
		final boolean valid = from.getBoolean( "isValid" );
		final int lvl = from.getInt( "level", 0 );
		final int min = from.getInt( "minutes", 0 );
		if( valid ) {
			return new EstimationResult( min, lvl, charging );
		} else {
			return new EstimationResult();
		}
	}

	public final boolean charging;
	public final boolean isValid;
	public final int level;

	public final int minutes;
	public final int remainingHours;
	public final int remainingMinutes;

	public EstimationResult() {
		this.minutes = -1;
		this.level = -1;
		this.charging = false;
		this.isValid = false;
		this.remainingHours = -1;
		this.remainingMinutes = -1;
	}

	public EstimationResult( final int minutes, final int level, final boolean charging ) {
		this.minutes = minutes;
		this.level = level;
		this.charging = charging;
		this.isValid = true;
		this.remainingHours = this.minutes > 0 ? (int) Math.floor( this.minutes / 60.0 ) : 0;
		this.remainingMinutes = this.minutes - (60 * this.remainingHours);
	}

	/**
	 * Converts an instance of this class into a bundle object to be send
	 * through the standard Android methods.
	 * 
	 * @since 0.8
	 * @return A bundle which contains all information wrapped by the object.
	 */
	public Bundle toBundle() {
		final Bundle returnBundle = new Bundle();
		returnBundle.putBoolean( "charging", this.charging );
		returnBundle.putBoolean( "isValid", this.isValid );
		returnBundle.putInt( "level", this.level );
		returnBundle.putInt( "minutes", this.minutes );
		returnBundle.putInt( "remainingHours", this.remainingHours );
		returnBundle.putInt( "remainingMinutes", this.remainingMinutes );
		return returnBundle;
	}

}