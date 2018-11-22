<%-- 
    Document   : Footer
    Created on : 19 oct. 2018, 06:29:56
    Author     : Maxence
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        String annee = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
%>

</div>
</main>
<footer class="page-footer light-green darken-4">
    <div class="container">
        <div class="row">
            <div class="col s3">
                <p><a href="/EquidaWebG2/ServletFooter/qui_sommes_nous" class="white-text"> Qui sommes-nous ? </a></p>
                <p><a href="/EquidaWebG2/ServletFooter/contact" class="white-text"> Contact </a></p>
            </div>

            <div class="col s2"> 
                <p><a href="/EquidaWebG2/ServletFooter/mentions_legales" class="white-text"> Mentions légales </a></p>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © <%= annee%> Copyright
        </div>
    </div>
</footer>   

<script type = "text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%= request.getContextPath()%>/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $(".dropdown-button").dropdown();
        $('.carousel').carousel({indicators: true, duration: 200});
        $('select').formSelect();
        $('.tooltipped').tooltip();
        $('.datepicker').datepicker({format: "yyyy-m-d"});
        $('.sidenav').sidenav();

        setTimeout(autoplayCaroussel, 15000);
        function autoplayCaroussel() {
            $('.carousel').carousel('next');
            setTimeout(autoplayCaroussel, 15000);
        }
    });
</script>
</body>
</html>
