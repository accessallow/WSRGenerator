<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            crossorigin="anonymous"
    />

    <!-- Optional theme -->
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
            integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
            crossorigin="anonymous"
    />

    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"
    ></script>

    <style>
        html *
        {
            font-size: 9pt !important;
            color: #000 !important;
            font-family: Arial !important;
        }
        .brown-color * {
            color:white !important;
            background-color: #554848;
        }
        .yellow-color * {
            color:black !important;
            background-color: #eec93c;
        }
        .epic-table tr:nth-child(1) *{
            color:white !important;
            background-color: #554848;
        }

        .plan-table tr:nth-child(1) *{
            color:white !important;
            background-color: #554848;
        }
        .issue-table tr:nth-child(1) *{
            color:white !important;
            background-color: #554848;
        }
        .white{
            background-color: #554848;
            color:white !important;
            margin-right:10px;
        }
        .green-status{
            background-color: green;
        }
        .amber-status{
            background-color: rgb(235, 211, 105);
        }
        .red-status{
            background-color: red;
        }
    </style>
</head>
<script>
    var edit = false;
    function editableToggle(){
        edit = !edit;
        document.getElementById("detailTable").contentEditable = edit;
    }
</script>
<body>
<div class="container-fluid">

    <table class="table table-bordered" id="detailTable">
        <tr class="brown-color">
            <td onclick="editableToggle()">Track</td>
            <td class="col-md-5">Key Activities in This Week</td>
            <!-- <td>Progress</td>
            <td>ETA</td> -->
            <td class="col-md-4">Deliverables & Value Adds in This Week</td>
            <td>Plan For Next Week</td>
            <td>Issue/Risks</td>
            <td>Status</td>
        </tr>
        <tr>
            <td style="text-align: center;vertical-align: middle;">BPI</td>
            <td>
                <table class="table table-bordered">
                    <tr class="brown-color">
                        <td>Activity</td>
                        <td style="width:6em;">Progress</td>
                        <td style="width:6em;">ETA</td>
                    </tr>

                    <tr class="yellow-color">
                        <td>${devActivities.tag}</td>
                        <td>${devActivities.track}</td>
                        <td>${devActivities.eta}</td>
                    </tr>

                    <#list devActivities.epics as e>
                        <tr>
                            <td><b>${e.tag}</b></td>
                            <td><b>${e.track}</b></td>
                            <td><b>${e.eta}</b></td>
                        </tr>
                        <#list e.issues as ei>
                            <tr>
                                <td>${ei.tag}</td>
                                <td>${ei.track}</td>
                                <td>${ei.eta}</td>
                            </tr>
                        </#list>
                    </#list>

                    <tr class="yellow-color">
                        <td>${testingActivities.tag}</td>
                        <td>${testingActivities.track}</td>
                        <td>${testingActivities.eta}</td>
                    </tr>

                    <#list testingActivities.issues as e>
                        <tr>
                            <td>${e.tag}</td>
                            <td>${e.track}</td>
                            <td>${e.eta}</td>
                        </tr>
                    </#list>


                </table>
            </td>
            <td>
                <#list values as p>
                    <table class="table table-bordered table-striped epic-table">
                        <tr><td>${p.tag}</td></tr>
                        <#list p.points as p>
                            <tr><td>${p}</td></tr>
                        </#list>
                    </table>
                </#list>

            </td>
            <td>

                <#list plans as p>
                    <table class="table table-bordered table-striped plan-table">
                        <tr><td>${p.tag}</td></tr>
                        <#list p.points as p>
                            <tr><td><span class="badge white">${p?index+1}</span>${p}</td></tr>
                        </#list>
                    </table>
                </#list>

            </td>
            <td>

                <#list risks as r>
                    <table class="table table-bordered table-striped issue-table">
                        <tr><td>${r.tag}</td></tr>
                        <#list r.points as p>
                            <tr><td><span class="badge white">${p?index+1}</span>${p}</td></tr>
                        </#list>
                    </table>
                </#list>

            </td>
            <td class="${status}-status">

            </td>
        </tr>
    </table>


</div>


</body>
</html>
