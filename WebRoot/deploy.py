import os

header = """<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
"""

if __name__ == '__main__':

    os.chdir('ace')
    # os.system('git pull cafe master')
    for item in os.listdir(os.getcwd()):
        if item.split('.')[-1] == 'html':
            name = '.'.join(item.split('.')[:-1]) + '.jsp'
            cont = header
            tag = False
            with open(item) as f:
                for line in f.readlines():
                    if tag == True:
                        cont += line
                    if line.strip() == '<head>':
                        tag = True
            with open(r'../' + name, 'w') as fw:
                fw.write(cont.replace('.html">', '.jsp">').replace('.html\'', '.jsp\'').replace('="../assets', '="assets'))



