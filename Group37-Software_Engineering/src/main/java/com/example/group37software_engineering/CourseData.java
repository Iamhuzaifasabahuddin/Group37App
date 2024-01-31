package com.example.group37software_engineering;

import com.example.group37software_engineering.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseData {
    public static List<Course> courseList = new ArrayList<>();

    public static void main() {
        courseList = new ArrayList<>();
        Course c1 = new Course();
        c1.setTitle("Getting Started with Enterprise-grade AI");
        c1.setImageUrl("https://skillsbuild.org/_next/image?url=https%3A%2F%2Fskillsbuild.org%2Fmedia%2F2023%2F02%2FGetting-Started-with-Enterprise-grade-AI.png&w=640&q=75");
        c1.setLink("https://www.ibm.com/academic/topic/artificial-intelligence?ach_id=256c0f15-a1f2-4b9e-b672-63f4d8b20018");
        c1.setCategory("Artificial Intelligence");
        c1.setDuration(5);
        Course c2 = new Course();
        c2.setTitle("Building Trustworthy AI Enterprise Solutions");
        c2.setImageUrl("https://skillsbuild.org/_next/image?url=https%3A%2F%2Fskillsbuild.org%2Fmedia%2F2023%2F02%2FBuilding-Trustworthy-AI-Enterprise-Solutions.png&w=640&q=75");
        c2.setLink("https://www.ibm.com/academic/topic/artificial-intelligence?ach_id=a0c78296-18d1-4e92-bebe-d31add4862e8&_gl=1*vt3f3l*_ga*OTYzODkxMTA0LjE3MDY2OTU2ODE.*_ga_FYECCCS21D*MTcwNjY5OTA4OC4yLjAuMTcwNjY5OTE1Ny4wLjAuMA..&_ga=2.218763857.977692365.1706695681-963891104.1706695681");
        c2.setCategory("Artificial Intelligence");
        c2.setDuration(10);
        Course c3 = new Course();
        c3.setTitle("Building AI Solutions Using Advanced Algorithms and Open Source Frameworks");
        c3.setImageUrl("https://skillsbuild.org/_next/image?url=https%3A%2F%2Fskillsbuild.org%2Fmedia%2F2023%2F02%2FBuilding-AI-Solutions-Using-Advanced-Algorithms-and-Open-Source-Frameworks.png&w=640&q=75");
        c3.setLink("https://www.ibm.com/academic/topic/artificial-intelligence?ach_id=d92c9dca-c230-4dfb-b214-d18c56104d5d&_gl=1*15y337k*_ga*OTYzODkxMTA0LjE3MDY2OTU2ODE.*_ga_FYECCCS21D*MTcwNjY5OTA4OC4yLjAuMTcwNjY5OTE1Ny4wLjAuMA..&_ga=2.11023100.977692365.1706695681-963891104.1706695681");
        c3.setCategory("Artificial Intelligence");
        c3.setDuration(20);
        Course c4 = new Course();
        c4.setTitle("Artificial Intelligence Fundamentals");
        c4.setImageUrl("https://skillsbuild.org/_next/image?url=https%3A%2F%2Fskillsbuild.org%2Fmedia%2F2023%2F07%2FArtificial-Intelligence-Fundamentals.png&w=640&q=75");
        c4.setLink("https://www.ibm.com/academic/topic/artificial-intelligence?ach_id=c7124e68-cce6-4dc4-b968-d65ba035a59f&_gl=1*qgejzo*_ga*OTYzODkxMTA0LjE3MDY2OTU2ODE.*_ga_FYECCCS21D*MTcwNjY5OTA4OC4yLjAuMTcwNjY5OTE1Ny4wLjAuMA..&_ga=2.216045278.977692365.1706695681-963891104.1706695681");
        c4.setCategory("Artificial Intelligence");
        c4.setDuration(10);
        courseList.addAll(List.of(c1, c2, c3, c4));
    }

}