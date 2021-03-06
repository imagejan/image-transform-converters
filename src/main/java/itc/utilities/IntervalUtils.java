package itc.utilities;

import mpicbg.spim.data.sequence.VoxelDimensions;
import net.imglib2.FinalRealInterval;
import net.imglib2.Interval;
import net.imglib2.RealInterval;
import net.imglib2.util.Intervals;

import java.util.Arrays;

import static net.imglib2.util.LinAlgHelpers.rows;

public class IntervalUtils
{
	public static RealInterval toCalibratedRealInterval(
			Interval interval,
			double voxelSpacing )
	{
		final double[] calibrations = new double[ interval.numDimensions() ];
		Arrays.fill( calibrations, voxelSpacing);

		return toCalibratedRealInterval( interval, calibrations );
	}

	public static RealInterval toCalibratedRealInterval(
			Interval interval,
			double[] voxelSpacings )
	{
		final double[] min = Intervals.minAsDoubleArray( interval );
		final double[] max = Intervals.maxAsDoubleArray( interval );

		final int numDimensions = min.length;
		for ( int d = 0; d < numDimensions; d++ )
		{
			min[ d ] *= voxelSpacings[ d ];
			max[ d ] *= voxelSpacings[ d ];
		}

		return new FinalRealInterval( min, max );
	}

	public static RealInterval toCalibratedRealInterval(
			Interval interval,
			VoxelDimensions voxelDimensions )
	{
		final double[] min = Intervals.minAsDoubleArray( interval );
		final double[] max = Intervals.maxAsDoubleArray( interval );

		final int numDimensions = min.length;
		for ( int d = 0; d < numDimensions; d++ )
		{
			min[ d ] *= voxelDimensions.dimension( d );
			max[ d ] *= voxelDimensions.dimension( d );
		}

		return new FinalRealInterval( min, max );
	}

	public static RealInterval scale(
			RealInterval realInterval,
			double scale )
	{
		final double[] scales = new double[ realInterval.numDimensions() ];
		Arrays.fill( scales, scale);

		return scale( realInterval, scales );
	}

	public static RealInterval scale(
			RealInterval realInterval,
			double[] scale )
	{
		final double[] min = Intervals.minAsDoubleArray( realInterval );
		final double[] max = Intervals.maxAsDoubleArray( realInterval );

		final int numDimensions = min.length;
		for ( int d = 0; d < numDimensions; d++ )
		{
			min[ d ] *= scale[ d ];
			max[ d ] *= scale[ d ];
		}

		return new FinalRealInterval( min, max );
	}

	public static String toString( RealInterval realInterval )
	{
		int n = realInterval.numDimensions();
		double[] min = Intervals.minAsDoubleArray( realInterval );
		double[] max = Intervals.maxAsDoubleArray( realInterval );

		final StringBuilder sb = new StringBuilder();

		final String className = realInterval.getClass().getSimpleName();
//		sb.append( className );

		sb.append( " (" );
		for ( int d = 0; d < n; d++ )
		{
			sb.append( min[ d ] );
			if ( d < n - 1 )
				sb.append( ", " );
		}
		sb.append( ") -- (" );
		for ( int d = 0; d < n; d++ )
		{
			sb.append( max[ d ] );
			if ( d < n - 1 )
				sb.append( ", " );
		}
		sb.append( ")" );

		return sb.toString();
	}

}
