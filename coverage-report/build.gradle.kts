plugins{
    id("jacoco-report-aggregation")
}

dependencies {
    jacocoAggregation(project(":java"))
    jacocoAggregation(project(":kotlin"))
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport"))
}

