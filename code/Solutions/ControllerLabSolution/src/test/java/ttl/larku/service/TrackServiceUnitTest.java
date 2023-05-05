package ttl.larku.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryTrackDAO;
import ttl.larku.domain.Track;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author whynot
 */
@ExtendWith(MockitoExtension.class)
public class TrackServiceUnitTest {

    @Mock
    private InMemoryTrackDAO trackDAO;

    @InjectMocks
    private TrackService trackService;

    @Test
    public void testCreateTrack() {
       Track track = Track.album("Sweet Memory")
               .artist("Charlie Smith")
               .build();

       Mockito.when(trackDAO.create(track)).thenReturn(track);

       Track newTrack = trackService.createTrack(track);

       Mockito.verify(trackDAO).create(track);
    }

    @Test
    public void testCreateTrackWithString() {
        Track track = Track.title("Sweet Memory")
                .build();

        Mockito.when(trackDAO.create(any())).thenReturn(track);

        Track newTrack = trackService.createTrack("Sweet Memory");

        Mockito.verify(trackDAO).create(any());
    }
}
