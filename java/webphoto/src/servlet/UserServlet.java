package servlet;

import dao.impl.AlbumDaoImpl;
import dao.impl.UserDaoImpl;
import model.User;
import util.SecUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = "/User")
public class UserServlet extends HttpServlet
{
    private UserDaoImpl userDao = new UserDaoImpl();

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errMsg = null;
        String errKind = null;
        User user = null;

        try
        {
            user = userDao.findByName(username);
        } catch (SQLException e)
        {
            //数据库异常 输出并返回错误信息
            errMsg = "数据库异常，请与管理员联系！";
            errKind = "databaseErrMsg";
            e.printStackTrace();
        }

        if (user == null)
        {
            //用户不存在 返回错误信息
            errMsg = "不存在该用户！";
            errKind = "usernameErrMsg";
        } else if (!user.getMd5().equals(SecUtil.md5(password)))
        {
            //密码错误 保存用户名 返回错误信息
            errMsg = "密码错误！";
            errKind = "passwordErrMsg";
        } else if (user.getActive() != User.ACTIVITED)
        {
            //用户状态为非激活状态
            errMsg = "当前账户未激活！";
            errKind = "activeErrMsg";
        }

        if (errMsg == null)
        {
            //成功登录 重定向到主页
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userid", user.getId());
            response.sendRedirect("index/index.jsp");
        } else
        {
            //登录失败 转发到登录页面
            request.setAttribute(errKind, errMsg);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //当前用户session无效化 转发到登录页
        request.setAttribute("username", request.getSession().getAttribute("username"));
        request.getSession().invalidate();
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String surePassword = request.getParameter("surePassword");
        String errMsg = null;
        String errKind = null;
        if (username == null || "".equals(username = username.trim()))
        {
            errMsg = "用户名不能为空";
            errKind = "usernameErrMsg";
        } else
        {
            try
            {
                //检测用户名是否重复
                if (userDao.findByName(username) != null)
                {
                    errMsg = "该用户名已经被使用！";
                    errKind = "usernameErrMsg";
                }
            } catch (SQLException e)
            {
                //数据库异常
                errMsg = "数据库异常，请与管理员联系！";
                errKind = "databaseErrMsg";
                e.printStackTrace();
            }

            if (password != null && surePassword != null && !password.equals("") && !surePassword.equals(""))
            {
                //密码不为空
                if (!password.equals(surePassword))
                {
                    //两次密码不相等
                    errMsg = "两次输入的密码不一致！";
                    errKind = "passwordErrMsg";
                }
            } else
            {
                errMsg = "两次输入的密码不能为空，且必须一致！";
                errKind = "passwordErrMsg";
            }

            if (errMsg == null)
            {
                //完成注册 转发到登录页面
                User user = new User(username, password);
                try
                {
                    if (userDao.insert(user) == 1)
                    {
                        //注册成功
                        request.setAttribute("username", username);
                        request.setAttribute("register", "true");
                        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                    }
                } catch (SQLException e)
                {
                    errMsg = "数据库异常，请与管理员联系！";
                    errKind = "databaseErrMsg";
                    e.printStackTrace();
                }
            }
        }

        //注册失败 记录错误信息 转发到注册页面
        request.setAttribute(errKind, errMsg);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("surePassword", surePassword);
        request.getRequestDispatcher("/user/register.jsp").forward(request, response);
    }

    private void center(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = null;
        try
        {
            user = userDao.findById((int) request.getSession().getAttribute("userid"));
            request.setAttribute("albumCount", new AlbumDaoImpl().findCountByUserId(user.getId()));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        request.setAttribute("regTime", user.getRegTime());
        if (user.getActive() == User.ACTIVITED)
        {
            request.setAttribute("isActive", "已激活");
        } else
        {
            request.setAttribute("isActive", "未激活");
        }

        String centerEditErrMsg = (String) request.getSession().getAttribute("centerEditErrMsg");
        String centerEditSucMsg = (String) request.getSession().getAttribute("centerEditSucMsg");
        if (centerEditErrMsg != null)
        {
            request.setAttribute("centerEditErrMsg", centerEditErrMsg);
            request.getSession().removeAttribute("centerEditErrMsg");
        }
        if(centerEditSucMsg != null)
        {
            request.setAttribute("centerEditSucMsg", centerEditSucMsg);
            request.getSession().removeAttribute("centerEditSucMsg");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/user/center.jsp");
        try
        {
            rd.forward(request, response);
            return;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void rename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String newUsername = request.getParameter("newUsername");
        try
        {
            if (newUsername == null || "".equals(newUsername = newUsername.trim()))
            {
                request.getSession().setAttribute("centerEditErrMsg", "修改后的昵称不能为空");
            } else
            {

                if (userDao.findByName(newUsername) != null)
                {
                    request.getSession().setAttribute("centerEditErrMsg", "该昵称已经被使用！");
                } else
                {
                    userDao.rename((int) request.getSession().getAttribute("userid"), newUsername);
                    request.getSession().setAttribute("username",newUsername);
                    request.getSession().setAttribute("centerEditSucMsg", "用户昵称修改成功！");
                }

            }
            response.sendRedirect(request.getContextPath() + "/User?method=center");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void rePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String sureNewPassword = request.getParameter("sureNewPassword");
        String centerEditErrMsg = null;
        try
        {
            if (oldPassword == null)
            {
                centerEditErrMsg = "当前密码不能为空";
            } else if (!SecUtil.md5(oldPassword).equals(
                    userDao.findById((int) request.getSession().getAttribute("userid")).getMd5()))
            {
                centerEditErrMsg = "当前密码不正确";
            }
            if (newPassword == null || sureNewPassword == null
                    || "".equals(newPassword) || "".equals(sureNewPassword))
            {
                centerEditErrMsg = "修改后的密码不能为空";
            } else if (!newPassword.equals(sureNewPassword))
            {
                centerEditErrMsg = "修改后的密码两次输入不一致";
            }
            if (centerEditErrMsg != null)
            {
                request.getSession().setAttribute("centerEditErrMsg", centerEditErrMsg);
            } else
            {

                userDao.rePassword((int) request.getSession().getAttribute("userid"),
                        SecUtil.md5(newPassword));
                request.getSession().setAttribute("centerEditSucMsg", "密码修改成功");
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/User?method=center");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String method = request.getParameter("method");
        if ("login".equals(method))
        {
            login(request, response);
        } else if ("logout".equals(method))
        {
            logout(request, response);
        } else if ("register".equals(method))
        {
            register(request, response);
        } else if ("center".equals(method))
        {
            center(request, response);
        } else if ("rename".equals(method))
        {
            rename(request, response);
        } else if ("rePassword".equals(method))
        {
            rePassword(request, response);
        }
    }
}
