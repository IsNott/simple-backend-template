package system.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import system.blog.entity.BlogComment;
import system.blog.entity.BlogPost;
import system.blog.entity.BlogType;
import system.blog.mapper.BlogCommentMapper;
import system.blog.mapper.BlogPostMapper;
import system.blog.mapper.BlogTypeMapper;
import system.blog.vo.CommentVO;
import system.blog.vo.PostVO;
import system.blog.vo.PosterInfoVO;
import system.comon.Result;
import system.user.entity.User;
import system.user.mapper.UserMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/blog/")
public class BlogPostController {

    @Resource
    private BlogPostMapper blogPostMapper;
    @Resource
    private BlogCommentMapper blogCommentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BlogTypeMapper blogTypeMapper;


    // 发送文章
    @RequestMapping("post")
    public Result postBlog(PosterInfoVO info){
        if(Objects.isNull(info)){
            return Result.fail("发送信息不能为空");
        }
        if(Objects.isNull(info.getId())){
            return Result.fail("发送人id不能为空");
        }
        User user = userMapper.selectById(info.getId());
        if(Objects.isNull(user) || !"2".equals(user.getUserType())){
            return Result.fail("用户不存在或者不是博主");
        }
        BlogPost blogPost = new BlogPost();
        blogPost.setTypeId(info.getTypeId());
        blogPost.setContext(info.getContext());
        blogPost.setCoverPath(info.getCoverPath());
        blogPost.setLikes(0L);
        blogPost.setTitle(info.getTitle());
        blogPost.setUserId(info.getId());
        blogPost.setSendTime(LocalDateTime.now());
        blogPost.setRecommendFlag("0");
        blogPost.setPostView(0L);
        blogPostMapper.insert(blogPost);
        return Result.ok();
    }

    // 点赞文章
    @RequestMapping("addLike")
    public Result addLike(long blogId){
        BlogPost blogPost = blogPostMapper.selectById(blogId);
        if(Objects.isNull(blogPost)){
            return Result.fail("点赞的文章不存在");
        }
        if(Objects.isNull(blogPost.getLikes())){
            blogPost.setLikes(1L);
        }else {
            Long likes = blogPost.getLikes();
            blogPost.setLikes(++likes);
        }
        blogPostMapper.updateById(blogPost);
        return Result.ok();
    }

    // 浏览全部/按博主id/类型文章列表
    @RequestMapping("postList")
    public Result postList(@Nullable Long userId,@Nullable Long typeId){
        LambdaQueryWrapper<BlogPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(userId) && userId > 0L, BlogPost::getUserId, userId)
                .eq(Objects.nonNull(typeId) && typeId > 0L, BlogPost::getTypeId, typeId);
        List<BlogPost> blogPosts = blogPostMapper.selectList(wrapper);
        List<PostVO> vos = null;
        if(!CollectionUtils.isEmpty(blogPosts)){
            vos = blogPosts.stream().map(post ->{
                PostVO postVO = new PostVO();
               if(Objects.nonNull(post.getUserId())){
                   BeanUtils.copyProperties(post,postVO);
                   User user = userMapper.selectById(post.getUserId());
                   postVO.setNickName(user.getNickName());
               }
                return postVO;
            }).collect(Collectors.toList());
        }
        return Result.okData(vos);
    }

    // 点击浏览文章
    @RequestMapping("view")
    public Result view(long postId){
        BlogPost blogPost = blogPostMapper.selectById(postId);
        if(Objects.isNull(blogPost)){
            return Result.fail("点击的文章不存在");
        }
        if(Objects.isNull(blogPost.getPostView())){
            blogPost.setPostView(1L);
        }else {
            Long view = blogPost.getPostView();
            blogPost.setPostView(++view);
        }
        return Result.ok();
    }

    // 添加文章评论
    @RequestMapping("comment")
    public Result comment(long postId,long userId,String comment){
        BlogPost blogPost = blogPostMapper.selectById(postId);
        if(Objects.isNull(blogPost)){
            return Result.fail("文章不存在");
        }
        BlogComment blogComment = new BlogComment();
        blogComment.setCommenterId(userId);
        blogComment.setCommentTime(LocalDateTime.now());
        blogComment.setPostId(postId);
        blogComment.setContext(comment);
        blogCommentMapper.insert(blogComment);
        return Result.ok();
    }

    // 更新评论
    @RequestMapping("updateComment")
    public Result updateComment(long commentId,String comment){
        BlogComment blogComment = blogCommentMapper.selectById(commentId);
        if(Objects.isNull(blogComment)){
            return Result.fail("评论不存在");
        }
        blogComment.setContext(comment);
        blogCommentMapper.updateById(blogComment);
        return Result.ok();
    }

    // 查看文章的评论列表
    @RequestMapping("commentList")
    public Result commentList(long postId){
        BlogPost blogPost = blogPostMapper.selectById(postId);
        if(Objects.isNull(blogPost)){
            return Result.fail("文章不存在");
        }
        LambdaQueryWrapper<BlogComment> wrapper = new LambdaQueryWrapper<BlogComment>().eq(BlogComment::getPostId, blogPost.getId());
        List<BlogComment> comments = blogCommentMapper.selectList(wrapper);
        List<CommentVO> commentVOList = comments.stream()
                .filter(blogComment -> Objects.nonNull(blogComment.getCommenterId()))
                .map(comment -> {
                    User user = userMapper.selectById(comment.getCommenterId());
                    CommentVO vo = new CommentVO();
                    BeanUtils.copyProperties(comment,vo);
                    vo.setSenderName(user.getNickName());
                    return vo;
                }).collect(Collectors.toList());

        return Result.okData(commentVOList);
    }

    // 删除评论
    @RequestMapping("removeComment")
    public Result removeComment(long commentId){
        BlogComment blogComment = blogCommentMapper.selectById(commentId);
        if(Objects.isNull(blogComment)){
            return Result.fail("评论不存在");
        }
        blogCommentMapper.deleteById(blogComment);
        return Result.ok();
    }

    @RequestMapping("typeList")
    public Result typeList(){
        List<BlogType> blogTypes = blogTypeMapper.selectList(new LambdaQueryWrapper<BlogType>());
        return Result.okData(blogTypes);
    }

    @RequestMapping("removeType")
    public Result removeType(long typeId){
        BlogType blogType = blogTypeMapper.selectById(typeId);
        if(Objects.isNull(blogType)){
            return Result.fail("类型不存在");
        }
        blogTypeMapper.deleteById(blogType);
        return Result.ok();
    }

    @RequestMapping("uptType")
    public Result uptType(String typeName,long typeId){
        BlogType blogType = blogTypeMapper.selectById(typeId);
        if(Objects.isNull(blogType)){
            return Result.fail("类型不存在");
        }
        blogType.setTypeName(typeName);
        blogTypeMapper.updateById(blogType);
        return Result.ok();
    }

    @RequestMapping("addType")
    public Result addType(String typeName){
        BlogType blogType = new BlogType().setTypeName(typeName);
        blogTypeMapper.insert(blogType);
        return Result.ok();
    }

    // 删除文章
    @RequestMapping("removePost")
    public Result removePost(long postId){
        BlogPost blogPost = blogPostMapper.selectById(postId);
        if(Objects.isNull(blogPost)){
            return Result.fail("文章不存在");
        }
        blogPostMapper.deleteById(postId);
        return Result.ok();
    }

    // 计算博主的点赞数
    @RequestMapping("countLike")
    public Result countLike(long userId){
        User user = userMapper.selectById(userId);
        if(Objects.isNull(user) || !"2".equals(user.getUserType())){
            return Result.fail("用户不存在或者不是博主");
        }
        LambdaQueryWrapper<BlogPost> queryWrapper = new LambdaQueryWrapper<BlogPost>().eq(BlogPost::getUserId, user.getId());
        List<BlogPost> blogPosts = blogPostMapper.selectList(queryWrapper);
        Long like = blogPosts.stream().mapToLong(BlogPost::getLikes).sum();
        return Result.okData(like);
    }

    // 文章上/取消推荐
    @RequestMapping("recommend")
    public Result recommend(long postId,String flag){
        BlogPost blogPost = blogPostMapper.selectById(postId);
        if(Objects.isNull(blogPost)){
            return Result.fail("文章不存在");
        }
        blogPost.setRecommendFlag(flag);
        blogPostMapper.updateById(blogPost);
        return Result.ok();
    }

}

