package servlet;

import dao.impl.PhotoDaoImpl;
import model.Photo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UploadPhotoServlet", urlPatterns = "/Upload")
public class UploadPhotoServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        FileItemFactory factory;
        factory = new DiskFileItemFactory();

        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 开始解析请求信息
        List items = null;
        try
        {
            items = upload.parseRequest(request);
        } catch (FileUploadException e)
        {
            e.printStackTrace();
        }

        Iterator iter = items.iterator();
        int albumId = 0;
        String fileName = null;
        String errMsg = null;

        while (iter.hasNext())
        {
            FileItem item = (FileItem) iter.next();

            // 信息为普通的格式
            if (item.isFormField())
            {
                albumId = Integer.parseInt(item.getString());
            }
            // 信息为文件格式
            else
            {
                fileName = item.getName();
                if (fileName.equals(""))
                {
                    errMsg = "";
                    continue;
                }
                String suffix = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
                String[] kind = {".jpg",".jepg",".png",".gif"};
                boolean find = false;
                for (String k: kind)
                {
                    if(suffix.equals(k))
                    {
                        find = true;
                        break;
                    }
                }
                if(!find)
                {
                    errMsg = "所选文件类型不支持，请导入jpg,jepg，png，gif类型的图片！";
                    continue;
                }
                fileName = new Date().getTime() + suffix;
                String basePath = getServletConfig().getServletContext().getRealPath("sources/");
                File file = null;
                try
                {
                    file = new File(basePath, fileName);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    item.write(file);
                    item.delete();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if (errMsg == null)
        {
            System.out.println("文件上传成功！");
            Photo photo = new Photo();
            photo.setAlbumid(albumId);
            photo.setPhotopath("/sources/" + fileName);
            try
            {
                new PhotoDaoImpl().insert(photo);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }else if (!errMsg.equals(""))
        {
            request.getSession().setAttribute("uploadErrMsg",errMsg);
        }
        response.sendRedirect(request.getContextPath() + "/Album?albumId=" + albumId);
    }
}

