package service;

import com.uh635c.task22.model.Post;
import com.uh635c.task22.model.PostStatus;
import com.uh635c.task22.model.Tag;
import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.PostRepository;
import com.uh635c.task22.service.PostService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class PostServiceTest {
    private static PostService postService;
    private static PostRepository postRepositoryMock;

    @BeforeClass
    public static void getPostService() {
        postRepositoryMock = Mockito.mock(PostRepository.class);
        postService = new PostService(postRepositoryMock);
    }

    @Test
    public void shouldReturnListOfPosts(){
        List<Post> postsActual = new ArrayList<>();
        postsActual.add(new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null)));
        postsActual.add(new Post(2L, "content2",Arrays.asList(new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(2L, null, null)));

        List<Post> postsExpected = new ArrayList<>();
        postsExpected.add(new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null)));
        postsExpected.add(new Post(2L, "content2",Arrays.asList(new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(2L, null, null)));

        Mockito.when(postRepositoryMock.getAll()).thenReturn(postsActual);

        Assert.assertEquals(postsExpected, postService.getAll());
    }

    @Test
    public void shouldReturnPostById(){
        Post postActual = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Post postExpected = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Mockito.when(postRepositoryMock.getById(Mockito.any())).thenReturn(postActual);

        Assert.assertEquals(postExpected, postService.getById(1L));
    }

    @Test
    public void shouldReturnSavedPost(){
        Post postActual = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Post postExpected = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Mockito.when(postRepositoryMock.save(postActual)).thenReturn(postActual);

        Assert.assertEquals(postExpected, postService.save(postActual));
    }

    @Test
    public void shouldReturnUpdatedPost(){
        Post postActual = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Post postExpected = new Post(1L, "content1",Arrays.asList(new Tag(1L, "tag1"), new Tag(2L, "tag2")),
                PostStatus.ACTIVE, new Writer(1L, null, null));

        Mockito.when(postRepositoryMock.update(postActual)).thenReturn(postActual);

        Assert.assertEquals(postExpected, postService.update(postActual));

    }

    @Test
    public void shouldCallPostRepositoryRemoveMethod(){
        postService.remove(1L);

        Mockito.verify(postRepositoryMock).remove(1L);
    }
}
