package com.iteratrlearning.problems.reactive_streams;

import com.iteratrlearning.examples.reactive_streams.Track;
import com.iteratrlearning.examples.reactive_streams.Tracks;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.junit.Test;

import java.util.Comparator;

public class OperationsExercise
{

    private final Flowable<Track> tracks = Flowable.fromArray((Tracks.allTracks));

    @Test
    public void canFindAllArtistsWithTracksOver200Seconds()
    {
        // TODO: find all artist names with tracks longer than 200 seconds.
        Flowable<String> names = tracks.filter(t -> t.getLengthInSeconds() > 200).map(Track::getArtist).distinct();
        names.test().assertResult(Tracks.LED_ZEPPELIN, Tracks.THE_BEATLES);
    }

    @Test
    public void isThereATrackByPinkFloyd()
    {
        // TODO: check if there is a track by pink floyd
        final Single<Boolean> names =   tracks.any(t -> t.getArtist().equals(Tracks.PINK_FLOYD));
        names.test().assertResult(true);
    }

    @Test
    public void canFindTheNameOfTheShortestTrackByLedZeppelin()
    {
        // TODO: find the name of the shortest track by Led Zeppelin

        Maybe<String> names = tracks.filter(t-> t.getArtist().equals(Tracks.LED_ZEPPELIN))
                .sorted(Comparator.comparingInt(Track::getLengthInSeconds))
                .elementAt(0)
                .map(Track::getName);

        names.test().assertResult("Rock and Roll");
    }
}
