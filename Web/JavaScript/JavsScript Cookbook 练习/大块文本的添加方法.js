<script type="text/javscript">
var txtArray = new Array();
txtArray.push("<tr>");
txtArray.push("<td>Boston</td><td>24</td><td>10</td><td>Partly Cloudy</td>");
txtArray.push("</tr>");
txtArray.push("<tr>");
txtArray.push("<td>New York</td><td>21</td><td>14</td><td>Snow</td>");
txtArray.push("</tr>");
document.getElementById("weatherTBody").innerHTML = txtArray.join("");
txtArray = null;
</script>