package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;

		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		background(0);	
	}
	int mode = 0;

	public void keyPressed() {
		if (key >= '0' && key <= '3') {
			mode = key - '0';
		}
		println(mode);
	}

	
	public void draw()
	{	
		background(0);
		switch (mode) {
			//bar chart
			case 1: 
				// Draw X-axis
				stroke(255);
				line(30, height-30, width-30, height-30); 
				
				// Draw Y-axis
				line(30, 30, 30, height-30); 
				float w = (width - 60) / (float)months.length; 
				for (int i = 0; i < months.length; i++) {
					float x = map(i, 0, months.length, 30, width - 30); 
					float y = map(rainfall[i], 0, max(rainfall), height - 30, 30);  
					//rainbow colours for bars
					float hue = map(i, 0, 16, 0, 300); 
					colorMode(HSB);
					fill(hue, 255, 255);
					rect(x, y, w, height - 30 - y);
					//labels for months
					fill(255);
					textAlign(CENTER, TOP);
					text(months[i], x + w/2, height - 20);

				}
				//labels for y-axis(from 0 to 500)
				textAlign(RIGHT, CENTER);
					for (int j = 0; j <= 500; j += 50) {
						float yPosition = map1(j, 0, 500, height - 30, 30);  
						line(25, yPosition, 30, yPosition);  
						text(Integer.toString(j), 25, yPosition); 
					}
				break;

			//trend line chart

			case 2:
				// Draw X-axis
				colorMode(HSB);
				stroke(255);
				line(30, height-30, width-30, height-30); 
				
				// Draw Y-axis
				line(30, 30, 30, height-30); 

				//labels for months
				float m = (width - 60) / (float) months.length;
				for (int i = 0; i < months.length; i++) {
					float x = map(i, 0, months.length, 30, width - 30);
					fill(255);
					textAlign(CENTER, TOP);
					text(months[i], x + m/2, height - 20);
				}

				//trend line on the x-y graph  
    
				for (int i = 0; i < months.length - 1; i++) {
					float x1 = map1(i, 0, months.length, 30, width - 30);
					float y1 = map1(rainfall[i], 0, max(rainfall), height - 30, 30);
				
					float x2 = map1(i + 1, 0, months.length, 30, width - 30);
					float y2 = map1(rainfall[i + 1], 0, max(rainfall), height - 30, 30);
			
					line(x1, y1, x2, y2);
				}

				//labels for y-axis(from 0 to 500)
				textAlign(RIGHT, CENTER);
				for (int j = 0; j <= 500; j += 50) {
					float yPosition = map1(j, 0, 500, height - 30, 30);  
					line(25, yPosition, 30, yPosition);  
					text(Integer.toString(j), 25, yPosition); 
				}
				break;

			//pie chart
			case 3:

				colorMode(HSB);

				//total value of all rainfall
				float tot = 0;
				for(float f:rainfall)
				{
					tot += f;
				}

				//drawing pie chart with rainbow hues
				float totalAngle = 0;
				for (int i = 0; i < months.length; i++) {
					float angle = map(rainfall[i], 0, tot, 0, TWO_PI);
					float hue = map(i, 0, months.length, 0, 255);
					fill(hue, 255, 255);
					arc(width / 2, height / 2, 300, 300, totalAngle, totalAngle + angle);
					totalAngle += angle;
				}


		}
	}
}
