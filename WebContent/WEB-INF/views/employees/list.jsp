<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>フォローリスト</h2>
        <c:choose>
            <c:when test="${follows_count == 0}">
                    <a>まだ誰もフォローしていません。</a>
                </c:when>
                <c:otherwise>
        <table id="follow_list">
            <tbody>

                <tr>
                    <th class="follow_code">社員番号</th>
                    <th class="follow_name">氏名</th>
                    <th class="follow_action">操作</th>
                </tr>

                <c:forEach var="employee" items="${followlist}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="follow_code"><c:out value="${employee.code}" /></td>
                        <td class="follow_name"><c:out value="${employee.name}" /></td>
                        <td class="follow_action">
                            <c:choose>
                                <c:when test="${employee.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <p><a href="<c:url value='/reports/rlist?id=${employee.id}' />">日報を表示</a></p>&nbsp;&nbsp;&nbsp;&nbsp;


                                    <div class="to_room">
                                    <form method="post" action="<c:url value='/follow/destroy?id=${employee.follow_id}' />" class="make_room">
                                     <input type="submit" name="submit" value="フォローリストから外す">
                                   </form>
                                     </div>

                                     <script>
                                     $(".to_room").each(function(){
                                            $(this).click(function(){
                                                var form = $(this).children('.make_room').submit();
                                            });
                                        });
                                     </script>

                                    <!--
                                    <p><a href="#" onclick="confirmDestroy();"><c:out value="${employee.id}" />フォローリストから外す</a></p>
                                    <form method="POST" action="<c:url value='/follow/destroy?id=${employee.id}' />" name="${employee.id}">
                                     </form>
                                     <script>
                                          function confirmDestroy() {
                                                if(confirm("本当に削除してよろしいですか？")) {
                                                    document.forms["name"].elements["name"].submit();
                                                         }
                                                }
                                      </script>
                                      -->
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <div id="pagination">
            （全 ${follows_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((follows_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/followlist?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:otherwise>
                </c:choose>
    </c:param>
</c:import>