import os
import sys

header = """<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">      
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
"""

if __name__ == '__main__':
    for item in os.listdir(os.getcwd() + r'/pages'):
        if item.split('.')[-1] == 'html':
            name = '.'.join(item.split('.')[:-1]) + '.jsp'

            cont = header
            tag = False
            with open(r'pages/' + item) as f:
                for line in f.readlines():
                    if tag == True:
                        cont += line
                    if line.strip() == '<head>':
                        tag = True
            with open(name, 'w') as fw:
                fw.write(cont.replace('.html">', '.jsp">').replace('="../static', '="static'))

    msg = 'push file to server & debug' if len(sys.argv) == 1 else ' '.join(sys.argv[1:])
    print os.popen('git commit -am "' + msg + '"').read()
    print os.popen('git push cafe master').read()
