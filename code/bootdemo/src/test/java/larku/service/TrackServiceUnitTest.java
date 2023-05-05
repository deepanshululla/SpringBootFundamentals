package larku.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Track;
import ttl.larku.jconfig.TrackerConfig;
import ttl.larku.service.TrackService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = TrackerConfig.class)
public class TrackServiceUnitTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private BaseDAO<Track> trackDao;

    @BeforeEach
    public void setup() {
        trackService.clear();
    }

    @Test
    public void testCreateTrack() {
        Track newTrack = trackService.createTrack("You Stepped Out Of A Dream", "Herb Ellis", "Three guitars in Bossa Nova Time",
                "04:19", "1963");
        Mockito.when(trackDao.create(Mockito.any())).thenReturn(newTrack);
        newTrack = trackService.createTrack(newTrack);

        Mockito.when(trackDao.get(newTrack.getId())).thenReturn(newTrack);
        Track result = trackService.getTrack(newTrack.getId());
        List<Track> tracks = new ArrayList<Track>();
        tracks.add(newTrack);
        Mockito.when(trackDao.getAll()).thenReturn(tracks);

        assertTrue(result.getTitle().contains(newTrack.getTitle()));
        assertEquals(1, trackService.getAllTracks().size());
        verify(trackDao, Mockito.times(1)).create(newTrack);
        verify(trackDao, Mockito.times(1)).get(newTrack.getId());
        verify(trackDao, Mockito.times(1)).getAll();
    }

    @Test
    public void testDeleteTrack() {
        Track track1 = Track.title("The Shadow Of Your Smile").artist("Big John Patton")
                .album("Let 'em Roll").duration("06:15").date("1965-03-04").build();
        Mockito.when(trackDao.create(Mockito.any())).thenReturn(track1);
        track1 = trackService.createTrack(track1);
        Track track2 = Track.title("I'll Remember April").artist("Jim Hall and Ron Carter")
                .album("Alone Together").duration("05:54").date("1972-03-04").build();

        Mockito.when(trackDao.get(track1.getId())).thenReturn(track1);

        trackService.deleteTrack(track1.getId());
        verify(trackDao, Mockito.times(1)).delete(track1);
        trackService.deleteTrack(track2.getId());
        verify(trackDao, Mockito.times(0)).delete(track2);
    }

    @Test
    public void testUpdateTrack() {
        Track track1 = Track.title("The Shadow Of Your Smile").artist("Big John Patton")
                .album("Let 'em Roll").duration("06:15").date("1965-03-04").build();

        Mockito.when(trackDao.create(Mockito.any())).thenReturn(track1);

        track1 = trackService.createTrack(track1);

        track1.setTitle("A Shadowy Smile");
        Mockito.when(trackDao.get(track1.getId())).thenReturn(track1);
        trackService.updateTrack(track1);
        verify(trackDao, Mockito.times(1)).update(track1);
    }
}
