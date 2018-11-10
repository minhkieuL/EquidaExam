<%-- 
    Document   : Footer
    Created on : 19 oct. 2018, 06:29:56
    Author     : Maxence
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    </div>
    <footer class="page-footer light-green darken-4">
        <div class="container">
            <div class="row">
                <div class="col s2">
                    <p><a href="/EquidaWebG2/ServletFooter/qui_sommes_nous" class="white-text"> Qui sommes-nous ? </a></p>
                    <p><a href="#" class="white-text"> Contact </a></p>
                </div>
                  
                <div class="col s2"> 
                    <p><a href="/EquidaWebG2/ServletFooter/mentions_legales" class="white-text"> Mentions legales </a></p>
                </div>
            </div>
        </div>
    </footer>   

    <script src="<%= request.getContextPath()%>/js/materialize.min.js"></script>
	
    <script> 
        $(document).ready(function(){
        $('.carousel').carousel({indicators: true, duration: 200});
		$('select').formSelect();
		$('.tooltipped').tooltip();
		$('.datepicker').datepicker({format : "yyyy-m-d"});
		
        setTimeout(autoplayCaroussel, 15000);
        function autoplayCaroussel() {
            $('.carousel').carousel('next');
            setTimeout(autoplayCaroussel, 15000);
        }
      });
    </script>
    </body>
</html>
