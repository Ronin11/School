namespace SensorSimulator
{
    partial class SimulationForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.serverLabel = new System.Windows.Forms.Label();
            this.serverEndPoint = new System.Windows.Forms.TextBox();
            this.lengthOfRaceLabel = new System.Windows.Forms.Label();
            this.lengthOfRace = new System.Windows.Forms.TextBox();
            this.milesLabel = new System.Windows.Forms.Label();
            this.numberOfSensorsLabel = new System.Windows.Forms.Label();
            this.numberOfSensors = new System.Windows.Forms.TextBox();
            this.startStopButton = new System.Windows.Forms.Button();
            this.exportButton = new System.Windows.Forms.Button();
            this.setupButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // serverLabel
            // 
            this.serverLabel.AutoSize = true;
            this.serverLabel.Location = new System.Drawing.Point(12, 21);
            this.serverLabel.Name = "serverLabel";
            this.serverLabel.Size = new System.Drawing.Size(90, 13);
            this.serverLabel.TabIndex = 0;
            this.serverLabel.Text = "Server End Point:";
            // 
            // serverEndPoint
            // 
            this.serverEndPoint.Location = new System.Drawing.Point(122, 18);
            this.serverEndPoint.Name = "serverEndPoint";
            this.serverEndPoint.Size = new System.Drawing.Size(108, 20);
            this.serverEndPoint.TabIndex = 1;
            this.serverEndPoint.Text = "127.0.0.1:14000";
            // 
            // lengthOfRaceLabel
            // 
            this.lengthOfRaceLabel.AutoSize = true;
            this.lengthOfRaceLabel.Location = new System.Drawing.Point(12, 48);
            this.lengthOfRaceLabel.Name = "lengthOfRaceLabel";
            this.lengthOfRaceLabel.Size = new System.Drawing.Size(84, 13);
            this.lengthOfRaceLabel.TabIndex = 2;
            this.lengthOfRaceLabel.Text = "Length of Race:";
            // 
            // lengthOfRace
            // 
            this.lengthOfRace.Location = new System.Drawing.Point(122, 45);
            this.lengthOfRace.Name = "lengthOfRace";
            this.lengthOfRace.Size = new System.Drawing.Size(43, 20);
            this.lengthOfRace.TabIndex = 3;
            this.lengthOfRace.Text = "100";
            // 
            // milesLabel
            // 
            this.milesLabel.AutoSize = true;
            this.milesLabel.Location = new System.Drawing.Point(171, 48);
            this.milesLabel.Name = "milesLabel";
            this.milesLabel.Size = new System.Drawing.Size(30, 13);
            this.milesLabel.TabIndex = 4;
            this.milesLabel.Text = "miles";
            // 
            // numberOfSensorsLabel
            // 
            this.numberOfSensorsLabel.AutoSize = true;
            this.numberOfSensorsLabel.Location = new System.Drawing.Point(12, 74);
            this.numberOfSensorsLabel.Name = "numberOfSensorsLabel";
            this.numberOfSensorsLabel.Size = new System.Drawing.Size(100, 13);
            this.numberOfSensorsLabel.TabIndex = 5;
            this.numberOfSensorsLabel.Text = "Number of Sensors:";
            // 
            // numberOfSensors
            // 
            this.numberOfSensors.Location = new System.Drawing.Point(122, 71);
            this.numberOfSensors.Name = "numberOfSensors";
            this.numberOfSensors.Size = new System.Drawing.Size(39, 20);
            this.numberOfSensors.TabIndex = 6;
            this.numberOfSensors.Text = "20";
            // 
            // startStopButton
            // 
            this.startStopButton.Location = new System.Drawing.Point(261, 69);
            this.startStopButton.Name = "startStopButton";
            this.startStopButton.Size = new System.Drawing.Size(191, 23);
            this.startStopButton.TabIndex = 7;
            this.startStopButton.Text = "Step 3 - Start Simulation";
            this.startStopButton.UseVisualStyleBackColor = true;
            this.startStopButton.Click += new System.EventHandler(this.startStopButton_Click);
            // 
            // exportButton
            // 
            this.exportButton.Location = new System.Drawing.Point(261, 43);
            this.exportButton.Name = "exportButton";
            this.exportButton.Size = new System.Drawing.Size(191, 23);
            this.exportButton.TabIndex = 10;
            this.exportButton.Text = "Step 2 - Export Data";
            this.exportButton.UseVisualStyleBackColor = true;
            this.exportButton.Click += new System.EventHandler(this.exportButton_Click);
            // 
            // setupButton
            // 
            this.setupButton.Location = new System.Drawing.Point(261, 14);
            this.setupButton.Name = "setupButton";
            this.setupButton.Size = new System.Drawing.Size(191, 23);
            this.setupButton.TabIndex = 11;
            this.setupButton.Text = "Step 1 -Setup Simulation";
            this.setupButton.UseVisualStyleBackColor = true;
            this.setupButton.Click += new System.EventHandler(this.setupButton_Click);
            // 
            // SimulationForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(472, 107);
            this.Controls.Add(this.setupButton);
            this.Controls.Add(this.exportButton);
            this.Controls.Add(this.startStopButton);
            this.Controls.Add(this.numberOfSensors);
            this.Controls.Add(this.numberOfSensorsLabel);
            this.Controls.Add(this.milesLabel);
            this.Controls.Add(this.lengthOfRace);
            this.Controls.Add(this.lengthOfRaceLabel);
            this.Controls.Add(this.serverEndPoint);
            this.Controls.Add(this.serverLabel);
            this.Name = "SimulationForm";
            this.Text = "Sensor Simulation - V1.2.2";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label serverLabel;
        private System.Windows.Forms.TextBox serverEndPoint;
        private System.Windows.Forms.Label lengthOfRaceLabel;
        private System.Windows.Forms.TextBox lengthOfRace;
        private System.Windows.Forms.Label milesLabel;
        private System.Windows.Forms.Label numberOfSensorsLabel;
        private System.Windows.Forms.TextBox numberOfSensors;
        private System.Windows.Forms.Button startStopButton;
        private System.Windows.Forms.Button exportButton;
        private System.Windows.Forms.Button setupButton;
    }
}

