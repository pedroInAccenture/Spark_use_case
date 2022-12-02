import boto3
import sys


def addStep(JobFlowId):
    print(JobFlowId)
    response = client.add_job_flow_steps(
        JobFlowId=JobFlowId,
        Steps=[
            {
                'Name': 'Spark application',
                'ActionOnFailure': 'CONTINUE',
                'HadoopJarStep': {
                    'Jar': 'command-runner.jar',
                    'Args': ["spark-submit", "--master", "yarn", "--deploy-mode", "cluster",
                             "--class", "mx.santander.dl2sf.joboperation.BalderJobOperator",
                             "s3://emrscriptsaccenture/jars/dl2sf-1.0.13.jar",
                             "TESTPEDRO1000", "20220101", "0", "s3://emrscriptsaccenture/config.json",
                             "DRO", "dm_academia", "academia", "dm_academia"]
                }
            },
        ],
        # ExecutionRoleArn='string'
    )
    print(response)


c = [
    {
        "Args": ["spark-submit", "--master", "yarn", "--deploy-mode", "cluster", "--class",
                 "com.usecase.ExecuteApp", "s3://emrscriptsaccenture/jars/file.jar",
                 "TESTPEDRO1000", "20220101", "0", "s3://emrscriptsaccenture/config.json", "DRO", "dm_academia",
                 "academia", "dm_academia"],
        "Type": "CUSTOM_JAR",
        "ActionOnFailure": "CONTINUE",
        "Jar": "command-runner.jar",
        "Properties": "",
        "Name": "Spark application"
    }
]

if __name__ == "__main__":
    print(">>>> executing file")
    AWS_REGION = sys.argv[1]
    AWS_ACCESS_KEY_ID = sys.argv[2]
    AWS_SECRET_ACCESS_KEY = sys.argv[3]

    client = boto3.client('emr', AWS_REGION,
                          aws_access_key_id=AWS_ACCESS_KEY_ID,
                          aws_secret_access_key=AWS_SECRET_ACCESS_KEY)

    print(">>>> checking resources file")
    for cluster in client.list_clusters()['Clusters']:
        state = cluster["Status"]["State"]
        print(state)
        if state in ["WAITING", "RUNNING"]:
            addStep(cluster["Id"])
        break

    print(">>>> end script to deploy")